<template>
  <div>
    <div id="eatery-grid" v-if="!$store.state.moreDetailsView">

      <div v-for="restaurant in restaurants" v-bind:key="restaurant.eatery_id" class="card-view"
        :style="{ backgroundImage: `url(${restaurant.image_url})` }">
        <div id="card-grid">
          <h2 class="restaurant-name">{{ restaurant.eatery_name }}</h2>
          <div id="status-container">
            <h3 id="status">
              {{ restaurant.isClosed ? "Sorry They're Closed" : "Open Now!" }}
            </h3>
            <h3 id="category">
              {{ restaurant.category }}
            </h3>
          </div>

          <div id="hours-container">
            <h3 id="open-time">
              Opens: {{ formatTime(restaurant.open_time) }}
            </h3>
            <h3 id="close-time">
              Closes: {{ formatTime(restaurant.close_time) }}
            </h3>
            <p id="city">
              In {{ restaurant.city }}
            </p>
          </div>


          <div v-if="getPathName === 'home'" :style="{ gridArea: 'buttons' }" class="votes">
            <div v-for="group in groups" :key="group.vote_id" :name="group.vote_id">

              <button @click="addEateryToVote(group.vote_id, restaurants.indexOf(restaurant))">
                {{ group.vote_name }}
              </button>
            </div>
          </div>



          <div v-if="getPathName === 'groups'" class="votes">
            <h3>{{ this.upVotes[restaurants.indexOf(restaurant)] }} Up Votes</h3>
            <h3>{{ this.downVotes[restaurants.indexOf(restaurant)] }} Down Votes</h3>
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
    <div v-if="$store.state.moreDetailsView" class="more-details-view">

      <h2 class="restaurant-name">{{ restaurantDetails.eatery_name }}</h2>
      <div class="more-status">
        <h3>
          Currently:{{ restaurantDetails.isClosed ? "Open Now" : "Closed" }}
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
        <h3 v-if="!$store.state.moreDetailsView">
          {{ restaurantDetails.city }}
        </h3>
        <h3>
          {{ restaurantDetails.category }}
        </h3>
      </div>
      <h3>
        {{ restaurantDetails.eatery_address }}
      </h3>
      <h3>
        {{ restaurantDetails.phone }}
      </h3>

      <a v-if="restaurantDetails.website !== null" :href="restaurantDetails.website" class="website-link">Link to their
        online menu!</a>

      <h3>
        {{ restaurantDetails.price }}
      </h3>
      <h3>
        {{ restaurantDetails.has_takeout ? "They have takeout!" : "No takeout " }} 😢
      </h3>
      <h3>
        <span v-for="n in Math.floor(restaurantDetails.rating)" :key="n">
          <i class="fa-solid fa-star"></i>
        </span>
        <span v-if="restaurantDetails.rating % 1 !== 0">
          <i class="fa-solid fa-star-half-alt"></i> <!-- Half-star for non-integer ratings -->
        </span>
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
  props: ["restaurants", "upVotes", "downVotes"],
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
      if (restaurant) {
        this.restaurantDetails = restaurant;
      } else {
        this.restaurantDetails = null;
      }
      this.$store.commit('TOGGLE_DETAILS_VIEW');
    },
    addEateryToVote(vote_id, pickedId) {
      let pickedRestaurant = this.restaurants[pickedId];

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
      } else if (pathName === 'groups' && this.$store.state.moreDetailsView) {
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
  }
};
</script>

<style>
.website-link {
  color: #f2fae6;
  background-color: #E6E6FA;
  /* Increase padding */
  box-shadow: 8px 8px 25px rgba(164, 36, 115, 0.8);
  /* Increase shadow intensity and transparency */
  font-size: 1.2em;
  /* Larger font size */
  font-weight: bold;
  /* Make text bolder */
  border: 2px solid #a42473;
  /* Add a contrasting border */
  cursor: pointer;
  transition: all 0.3s ease;
  /* Smooth transition for hover effects */
  cursor: pointer;
}

a:hover {
  background-color: #f88f1f;

  box-shadow: 10px 10px 30px rgba(164, 36, 115, 1);

  transform: scale(1.05);

}

button {
  background-color: #8A2BE2;
  color: white;
  border: none;
  border-radius: 30px;
  margin: 5px;
}

.card-view {
  display: grid;
  align-items: stretch;
  border-radius: 10px;
  border: 5px solid #8A2BE2;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 26vw;
  min-height: 70vh;
}

.more-details-view {
  grid-column: span 3;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  border-radius: 10px;
  border: 10px solid #00FFFF;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 80vw;
  max-height: 70vh;
  background: linear-gradient(50deg,

      #00FFFF,

      #228B22,

      #FF69B4,

      #8A2BE2,

      #7FFF00,

      #f7a029);
}

.more-details-view div {
  border: 5px solid #8A2BE2;
}

.more-details-view .more-status {
  display: grid;
  grid-column: span 1;
  height: 100%;
  background-color: #8A2BE2;
  max-height: 12vh;
  border-radius: 30px;
  padding: 4px;
  justify-content: center;
  width: 80%;
}

.more-details-view h3 {
  padding: 0 20px;
}

.website-link {
  font-size: 1.2em;
  text-decoration: none;
  color: #8A2BE2;
  background-color: #00FFFF;
  border-radius: 30px;
  justify-self: center;
  align-self: center;
  padding: 5px 10px;
}

#card-grid {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: stretch;
  align-items: start;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  justify-content: center;
  align-items: stretch;
}

.restaurant-name {
  grid-column: span 3;
  border-radius: 5px;
  margin: 0;
  background-color: #22b6b2;
  border-radius: 30px;
  padding: 1vw;
}

.votes {
  border-radius: 5px;
  margin: 0;
}

#status-container {
  grid-column: span 3;
  background-color: rgb(247, 160, 41, 0.55);
  padding: 10px;
  border-radius: 10px;
  text-align: center;
}

#status {
  color: #2f855a;
  /* Green for open */
}

#status-container #status:contains("Closed") {
  color: #c53030;
  /* Red for closed */
}

/* Category container styling */
#category-container {
  text-align: center;
  background-color: rgb(247, 160, 41, 0.55);
  padding: 5px;
  border-radius: 10px;
  text-align: center;
}

#category {
  font-weight: bold;
}

#hours-container {
  grid-column: span 2;
  display: grid;
  grid-template-columns: 1fr 1fr;
  background-color: rgb(247, 160, 41, 0.55);
  border-radius: 30%;
  padding: 2px;
}

#open-time,
#close-time {
  color: #8A2BE2;
  /* Dark gray */
}

#city {
  grid-column: span 2;
  color: #00FFFF;
  /* Dark gray */
}
</style>