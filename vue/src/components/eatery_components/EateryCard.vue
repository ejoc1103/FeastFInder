<template>
  <div id="eatery-grid">
    <div v-for="restaurant in restaurants" v-bind:key="restaurant.id" id="card-view">
      <h5 :style="{ gridArea: 'name' }">{{ restaurant.name }}</h5>
      <img :style="{ gridArea: 'img' }" :src="restaurant.imageUrl" alt="restaurant image" />
      <p :style="{ gridArea: 'status' }">{{ restaurant.status ? "Open Now" : "Closed" }}</p>
      <p :style="{ gridArea: 'cuisine' }">{{ restaurant.cuisine }}</p>
      <p :style="{ gridArea: 'isOpen' }">{{ restaurant.isOpen }}</p>
      <p :style="{ gridArea: 'openTime' }">{{ restaurant.openTime }}</p>
      <p :style="{ gridArea: 'closeTime' }">{{ restaurant.closeTime }}</p>
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
  border-radius: 10px;
  border: 5px solid #FF6F61;
  background-color: rgb(66, 66, 66, .5);
  gap: 0 5px;
  padding: 5px;
  margin: 5px;
  max-height: 250px;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  gap: 20px;
  justify-items: center;
  align-items: stretch;
  height: 100%;
}

img {
  max-width: 60%;
}
</style>