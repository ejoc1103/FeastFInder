<template>
  <div id="eatery-grid">
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.eatery_id" id="card-view"
      :style="{ backgroundImage: `url(${restaurant.image_url})` }">
      <div id="card-grid">
        <h2 :style="{ gridArea: 'name' }">{{ restaurant.eatery_name }}</h2>

        <p :style="{ gridArea: 'isClosed' }">
          {{ restaurant.isClosed ? "Open Now" : "Closed" }}
        </p>

        <p :style="{ gridArea: 'category' }">{{ restaurant.category }}</p>

        <p :style="{ gridArea: 'openTime' }">
          Opens: {{ formatTime(restaurant.open_time) }}
        </p>

        <p :style="{ gridArea: 'closeTime' }">
          Closes: {{ formatTime(restaurant.close_time) }}
        </p>
        <p :style="{ gridArea: 'city' }">{{ restaurant.city }}</p>

        <div v-if="getPathName === 'home'" :style="{ gridArea: 'buttons' }" class="votes">
          <div v-for="group in groups" :key="group.vote_id" :name="group.vote_id">
            {{ group.vote_name }}
            <button @click="addEateryToVote(group.vote_id, restaurant.eatery_id)">
              Add to Group
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

          <button @click="showMoreInfo">{{ this.showMoreOrLess ? "Show Less" : "Show More" }}</button>
          <p>
            {{ moreDetailsView ? restaurant.eatery_address : "" }}
          </p>
          <p>
            {{ moreDetailsView ? restaurant.phone : "" }}
          </p>
          <p>
            {{ moreDetailsView ? restaurant.website : "" }}
          </p>
          <p>
            {{ moreDetailsView ? restaurant.price : "" }}
          </p>
          <p>
            {{ moreDetailsView ? restaurant.has_takeout : "" }}
          </p>
          <p>
            {{ moreDetailsView ? restaurant.rating : "" }}
          </p>

        </div>
      </div>
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
    };
  },
  props: ["restaurants"],
  methods: {
    castVote(vote, eatery_id) {
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
    showMoreInfo() {
      this.moreDetailsView = !this.moreDetailsView;
    },
    addEateryToVote(vote_id, eatery_id) {
      this.newRestaurant = this.restaurants[eatery_id];
      VoteService.addEatery(vote_id, this.newRestaurant)
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
      console.log(this.$route.name);
      let pathName = this.$route.name;
      return pathName;
    },
    getGroups() {
      return this.$store.state.groups;
    },

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
        console.log(response.data);
        this.groups = response.data;
      })
      .catch((e) => {
        console.log(e);
      });
  },
};
</script>

<style>
#card-view {
  border-radius: 10px;
  border: 5px solid #7fff00;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 27vw;
}

#card-grid {
  display: grid;
  grid-template-areas:
    "name name name"
    "isClosed openTime openTime"
    "city closeTime closeTime"
    "buttons buttons buttons"
    "category category category";
  justify-items: center;
  background-color: rgb(255, 105, 180, 0.5);
  height: 100%;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  justify-items: center;
}

h2 {
  font-size: 2em;
  border-radius: 5px;
  margin: 0;
}

p {
  font-size: 1.4em;
  border-radius: 2px;
  margin: 0;
}

.votes {
  border-radius: 5px;
  margin: 0;
}
</style>