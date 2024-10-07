<template>
  <div id="eatery-grid">
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.eatery_id" id="card-view"
      :style="{ backgroundImage: `url(${restaurant.image_url})` }">
      <div id="card-grid">

        <h2 :style="{ gridArea: 'name' }">{{ restaurant.eatery_name }}</h2>


        <p :style="{ gridArea: 'isClosed' }">{{ restaurant.isClosed ? "Open Now" : "Closed" }}</p>


        <p :style="{ gridArea: 'category' }">{{ restaurant.category }}</p>

        <p :style="{ gridArea: 'openTime' }">{{ restaurant.open_time.slice(0, restaurant.open_time.indexOf(' '))
          }}</p>

        <p :style="{ gridArea: 'closeTime' }">{{ restaurant.close_time.slice(0, restaurant.close_time.indexOf(' '))
          }}</p>
        <!-- //Place holder for now we can change to all city from yelp 
        for smaller view and use this somewhere from more info without the slice -->
        <div @click="seeAddress = !seeAddress">
          <p v-if="!seeAddress" :style="{ gridArea: 'address' }">{{
      restaurant.eatery_address.slice(0, 10) }} </p>
          <p v-if="seeAddress" :style="{ gridArea: 'address' }">{{
      restaurant.eatery_address }} </p>
        </div>
        <!-- 
  <p>{{ restaurant.phone }}</p>
  
  <p>{{ restaurant.website }}</p>
  
  <p>{{ restaurant.has_takeout }}</p> -->

        <div v-if="getPathName === 'home'" :style="{ gridArea: 'buttons' }" class="votes">
          <div v-for="group in getGroups" :key="group.vote_id" :name="group.vote_id">{{ group.vote_name }}
            <button @click="addEateryToVote(group.vote_id, restaurant.eatery_id)">Add to Group</button>
          </div>

        </div>

        <div v-if="getPathName === 'groups'" class="votes">
          <h3>4 Up Votes</h3>
          <h3>4 Down Votes</h3>
        </div>

        <div v-if="getPathName === 'voting'" class="votes">
          <button>Thumbs Up</button>
          <button>Thumbs Down</button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newRestaurant: {
        eatery_id: "",
        eatery_name: "",
        image_url: "",
        isClosed: false,
        phone: "",
        category: "",
        open_time: "",
        close_time: "",
        eatery_address: "",
        thumbs_up: 0,
        thumbs_down: 0,
      },
      seeAddress: false,
    }
  },
  props: [
    "restaurants",
  ],
  methods: {
    addEateryToVote(vote_id, eatery_id) {
      this.newRestaurant = this.restaurants[eatery_id];
      console.log(this.newRestaurant);
      this.$store.commit('ADD_EATERY_TO_VOTE', { id: vote_id, eatery: this.newRestaurant });
    }
  },
  computed: {
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
  // created() {
  //   getGroups() {
  //     real call to a service will go here 
  //   }
  // }
};
</script>

<style>
#card-view {
  border-radius: 10px;
  border: 5px solid #7FFF00;
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
    "isClosed openTime closeTime"
    "category category category"
    "address address address"
    "buttons buttons buttons";
  justify-items: center;
  background-color: rgb(255, 105, 180, .5);
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