<template>
  <div id="card-view">
    <h5 :style="{ gridArea: 'name' }">{{ restaurant.name }}</h5>
    <img :style="{ gridArea: 'img' }" :src="restaurant.img" alt="restaurant image" />
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
</template>

<script>
export default {
  // props: ['restaurant'],
  data() {
    return {
      restaurant: {
        name: "McDonald's",
        status: true,
        cuisine: "Fast Food",
        openTime: "8:00 AM",
        closeTime: "10:00 PM",
        isOpen: "True",
        address: "123 Main St",
        phone: "123-456-7890",
        img: "https://s3-media4.fl.yelpcdn.com/bphoto/9O7uFG4mR8quqynmj-vtiA/o.jpg",
      },
    }
  }

}
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

img {
  max-width: 60%;
}
</style>