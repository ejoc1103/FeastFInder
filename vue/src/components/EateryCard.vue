<template>
  <div>
    <h1>{{restaurant.name}}</h1>
    <img :src="this.img" alt="restaurant image" />
    <p>{{ restaurant.status ? "Open Now" : "Closed" }}</p>
    <p>{{ restaurant.cuisine }}</p>
    <p>{{ restaurant.openTime  }}</p>
    <p>{{ restaurant.closeTime }}</p>
    <p>{{ restaurant.address }}</p>
    <button>Call to Order</button>
    <button>Add to Group</button>
    
  </div>
</template>

<script>
export default {
    data() {
        return {
            restaurant: {
                cuisine: "Mediterranean",
                name: "Simply Greek",
                status: true,
                img: "../dinerBackground.jpg",
                openTime: "7:00 AM",
                closeTime: "9:00 PM",
                address: "1234 Main St, Anytown, USA"
            }
        }
    }
}
</script>

<style>

</style>