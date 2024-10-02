<template>
  <div id="eatery-grid">
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.id" id="card-view"
      :style="{ backgroundImage: `url(${restaurant.imageUrl})` }">
      <h5 :style="{ gridArea: 'name' }">{{ restaurant.name }}</h5>
      <p :style="{ gridArea: 'status' }">{{ restaurant.status ? "Open Now" : "Closed" }}</p>
      <p :style="{ gridArea: 'cuisine' }">{{ restaurant.cuisine }}</p>
      <p :style="{ gridArea: 'isOpen' }">{{ restaurant.isOpen }}</p>
      <p :style="{ gridArea: 'openTime' }">{{ restaurant.openTime.slice(0, restaurant.openTime.indexOf(' ')) }}</p>
      <p :style="{ gridArea: 'closeTime' }">{{ restaurant.closeTime.slice(0, restaurant.closeTime.indexOf(' ')) }}</p>
      <p :style="{ gridArea: 'address' }">{{ restaurant.address }}</p>
      <p :style="{ gridArea: 'phone' }">{{ restaurant.phone }}</p>
      <div :style="{ gridArea: 'buttons' }">
        <button>Call to Order</button>
        <button>Add to Group</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    "restaurants",
  ],
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
  border: 5px solid #FF6F61;
  gap: 0 5px;
  padding: 5px;
  margin: 5px;
  max-height: 250px;
  background-size: cover;
  width: 90%;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 20px;
  justify-items: center;
  align-items: stretch;
}

img {
  max-width: 80%;
  max-height: 80%;
}

h5 {
  font-size: 1.6em;
  color: white;
  background-color: rgba(255, 111, 97, 0.75);
  border-radius: 5px;
  padding: 5px;
  margin: 0;
}

p {
  font-size: 1.2em;
  color: white;
  background-color: rgba(255, 111, 97, 0.5);
  border-radius: 2px;
  padding: 2px;
  margin: 0;
}
</style>