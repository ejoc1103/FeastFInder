<template>
  <div id="card-view">
    <h1 :style="{gridArea: 'name'}">{{ restaurant.name }}</h1>
    <img :style="{gridArea: 'img'}" :src="restaurant.img" alt="restaurant image" />
    <p :style="{gridArea: 'status'}">{{ restaurant.status ? "Open Now" : "Closed" }}</p>
    <p :style="{gridArea: 'cuisine'}">{{ restaurant.cuisine }}</p>
    <p :style="{gridArea: 'openTime'}">{{ restaurant.openTime }}</p>
    <p :style="{gridArea: 'closeTime'}">{{ restaurant.closeTime }}</p>
    <p :style="{gridArea: 'address'}">{{ restaurant.address }}</p>
    <p :style="{gridArea: 'phone'}">{{ restaurant.phone }}</p>
    <button>Call to Order</button>
    <button>Add to Group</button>
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
  #card-view{
    display: grid;
    grid-template-areas: 
      "name name"
      "img cuisine"
      "img status"
      "img phone"
      "openTime closeTime"
      "address address"
      "call add";
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-radius: 20px;
    background-color:#FF6F61;;
    gap: 0 10px;
    padding: 20px;
    margin: 20px;
  }
  img{
    width: 90%;
  }
</style>