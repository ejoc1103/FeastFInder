<template>
  <div id="eatery-grid">
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.id" id="card-view"
      :style="{ backgroundImage: `url(${restaurant.imageUrl})` }">


      <h5 :style="{ gridArea: 'name' }">{{ restaurant.name }}</h5>


      <p :style="{ gridArea: 'status' }">{{ restaurant.status ? "Open Now" : "Closed" }}</p>

      <p :style="{ gridArea: 'phone' }">{{ restaurant.phone }}</p>

      <p :style="{ gridArea: 'cuisine' }">{{ restaurant.cuisine }}</p>


      <p :style="{ gridArea: 'isOpen' }">{{ restaurant.isOpen }}</p>


      <!-- <p :style="{ gridArea: 'openTime' }">{{ restaurant.openTime.slice(0, restaurant.openTime.indexOf(' '))
        }}</p>


      <p :style="{ gridArea: 'closeTime' }">{{ restaurant.closeTime.slice(0, restaurant.closeTime.indexOf(' '))
        }}</p> -->


      <p :style="{ gridArea: 'address' }">{{ restaurant.address }}</p>



      <div v-if="!isVoting" :style="{ gridArea: 'buttons' }">
        <button>More Info</button>
        <button>Add to Group</button>
      </div>

      <h2 v-else class="votes">3 Votes</h2>

    </div>
  </div>
</template>

<script>
export default {
  props: [
    "restaurants",
  ],
  computed: {
    isVoting() {
      let pathName = this.$route.name;
      if (pathName === "groups") {
        return true;
      } else {
        return false;
      }
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
  }
};
</script>

<style>
#card-view {
  display: grid;
  grid-template-areas:
    "name name name"
    "img cuisine status"
    "img phone phone"
    "isOpen openTime closeTime"
    "address address address"
    "buttons buttons buttons";
  justify-items: center;
  align-content: center;
  align-items: start;
  text-align: center;
  border-radius: 10px;
  border: 5px solid #7FFF00;
  padding: 5px;
  margin: 5px;
  background-size: cover;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 20px;
  justify-items: center;
}

h5 {
  font-size: 1.2em;
  background-color: rgb(138, 43, 226, 0.5);
  color: #00FFFF;
  border-radius: 5px;
  padding: 5px;
  margin: 0;
}

p {
  font-size: 1em;
  background-color: rgb(138, 43, 226, 0.5);
  color: #00FFFF;
  border-radius: 2px;
  padding: 2px;
  margin: 0;
}

.votes {
  font-size: 1em;
  background-color: rgb(138, 43, 226, 0.5);
  color: #00FFFF;
  border-radius: 5px;
  padding: 5px;
  margin: 0;
}
</style>