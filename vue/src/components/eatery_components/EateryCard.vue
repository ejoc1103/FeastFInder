<template>
  <div>
    <div id="eatery-grid" v-if="!moreDetailsView">

      <div v-for="restaurant in restaurants" v-bind:key="restaurant.eatery_id" class="card-view"
        :style="{ backgroundImage: `url(${restaurant.image_url})` }">
        <div id="card-grid">
          <h2 class="restaurant-name">{{ restaurant.eatery_name }}</h2>
          <div>
            <h3>
              Currently:{{ restaurant.isClosed ? "Open Now" : "Closed" }}
            </h3>
          </div>

          <div>
            <h3>
              <!-- Only some times works we'll need to edit this -->
              {{ restaurant.category.slice(0, restaurant.category.indexOf(" ")) }}
            </h3>
          </div>

          <div>
            <h3>
              Opens: {{ formatTime(restaurant.open_time) }}
            </h3>
            <h3>
              Closes: {{ formatTime(restaurant.close_time) }}
            </h3>
          </div>
          <div>
            <h3>
              {{ restaurant.city }}
            </h3>
          </div>

          <div v-if="getPathName === 'home'" :style="{ gridArea: 'buttons' }" class="votes">
            <div v-for="group in groups" :key="group.vote_id" :name="group.vote_id">

              <button @click="addEateryToVote(group.vote_id, restaurant.eatery_id)">
                {{ group.vote_name }}
              </button>
            </div>
          </div>



          <div v-if="getPathName === 'groups'" class="votes">
            <h3>4 Up Votes</h3>
            <h3>4 Down Votes</h3>
          </div>
          <!-- Current Work Place  -->
          <div v-if="getPathName === 'voting'" class="votes">
            <button @click="castVote(true, restaurant.eatery_id)">Thumbs Up</button>
            <button @click="castVote(false, restaurant.eatery_id)">Thumbs Down</button>
          </div>
          <div>

            <button @click="showMoreInfo(restaurant)">{{ this.showMoreOrLess ? "Show Less" : "Show More" }}</button>
          </div>

        </div>
      </div>

    </div>
    <div v-if="moreDetailsView" class="more-details-view">

      <h2 class="restaurant-name">{{ restaurantDetails.eatery_name }}</h2>
      <div>
        <h3>
          Currently:{{ restaurantDetails.isClosed ? "Open Now" : "Closed" }}
        </h3>
      </div>

      <div>
        <h3>
          <!-- Only some times works we'll need to edit this -->
          {{ restaurantDetails.category }}
        </h3>
      </div>

      <div>
        <h3>
          Opens: {{ formatTime(restaurantDetails.open_time) }}
        </h3>
        <h3>
          Closes: {{ formatTime(restaurantDetails.close_time) }}
        </h3>
      </div>
      <div>
        <h3>
          {{ restaurantDetails.city }}
        </h3>
      </div>
      <h3>
        {{ restaurantDetails.eatery_address }}
      </h3>
      <h3>
        {{ restaurantDetails.phone }}
      </h3>
      <h3>
        {{ restaurantDetails.website }}
      </h3>
      <h3>
        {{ restaurantDetails.price }}
      </h3>
      <h3>
        {{ restaurantDetails.has_takeout }}
      </h3>
      <h3>
        {{ restaurantDetails.rating }}
      </h3>
      <button @click="showMoreInfo()">Show All</button>
    </div>

  </div>



</template>

<script>
import VoteService from "../../services/VoteService";
export default {
  data() {
    return {
      showMoreOrLess: false,
      moreDetailsView: false,
      newRestaurant: {
        eatery_id: "",
        eatery_name: "",
        image_url: "",
        eatery_address: "",
        category: "",
        website: "",
        open_time: "",
        close_time: "",
        has_takeout: false,
        rating: 0,
        phone: "",
        price: "",
        isClosed: false,
        city: "",
      },
      seeAddress: false,
      groups: [],
      restaurantDetails: {},
    };
  },
  props: ["restaurants"],
  methods: {
    castVote(vote, eatery_id) {
      console.log(eatery_id);
      VoteService.castVote(vote, eatery_id, this.$store.state.voter_id)
        .then((response) => {
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    showMoreOrLessMethod() {
      this.showMoreOrLess = !this.showMoreOrLess;
    },
    showMoreInfo(restaurant) {
      console.log(restaurant);
      if (restaurant) {
        this.restaurantDetails = restaurant;
      } else {
        this.restaurantDetails = null;
      }
      this.moreDetailsView = !this.moreDetailsView;
    },
    //Error is coming from somewhere around here Joe please take a look I
    // couldn't figure it out
    addEateryToVote(vote_id, pickedId) {
      console.log(this.restaurants);
      let pickedRestaurant = this.restaurants.filter((restaurant) => restaurant.eatery_id === pickedId);

      console.log(pickedId);
      VoteService.addEatery(vote_id, pickedRestaurant)
        .then((response) => {
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    formatTime(time) {
      let [hours, minutes] = time.split(":");

      if (!minutes) {
        hours = time.slice(0, 2);
        minutes = time.slice(2, 4);
      }
      hours = parseInt(hours, 10);
      let suffix = hours >= 12 ? "PM" : "AM";
      hours = hours % 12 || 12;
      return `${hours}:${minutes} ${suffix}`;
    },
  },
  computed: {
    buttonName() {
      return this.showMoreOrLess ? "Show Less" : "Show More";
    },
    getPathName() {
      let pathName = this.$route.name;
      return pathName;
    },
    checkPathName() {
      let pathName = this.$route.name;

      if (pathName === 'home') {
        return 'card-view';
      } else if (pathName === 'groups' && this.moreDetailsView) {
        return 'more-details-view';
      } else if (pathName === 'voting') {
        return 'voting-view';
      }
      return pathName;
    }
    // getGroups() {
    //   return this.$store.state.groups;
    // },

    // getTime(time) {
    //   let string = time.slice(0, this.restaurants[0].openTime.indexOf(' '));
    //   if (string > '1200') {
    //     string = string.slice(0, 2) - 12 + ':' + string.slice(2);
    //     string += ' PM';
    //   } else {
    //     string += ' AM';
    //   }
    //   console.log(string);
    //   return string;
    // }
  },
  created() {
    this.groups = VoteService.getVotes()
      .then((response) => {
        this.groups = response.data;
      })
      .catch((e) => {
        console.log(e);
      });
  },
};
</script>

<style>
.card-view {
  border-radius: 10px;
  border: 5px solid #7fff00;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 26vw;
}

.more-details-view {
  grid-column: span 3;
  display: flex;
  flex-wrap: wrap;
  border-radius: 10px;
  border: 5px solid #7fff00;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  background-color: rgb(234, 97, 144, 0.6);
  width: 80vw;
}

#card-grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: start;
  background-color: rgba(255, 105, 180, 0.5);
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  justify-items: center;
}

.restaurant-name {
  border-radius: 5px;
  margin: 0;
  background-color: #22b6b2;
  border-radius: 30px;
  padding: 1vw;
}

h2 {
  border-radius: 5px;
  margin: 0;
  background-color: #22b6b2;
  border-radius: 30px;
}

p {
  border-radius: 2px;
  margin: 0;
}

.votes {
  border-radius: 5px;
  margin: 0;
}
</style>