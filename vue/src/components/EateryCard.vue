<template>
  <div>
    <h1>{{restaurant.name}}</h1>
    <p>{{ restaurant.status }}</p>
    <p>{{ restaurant.cuisine }}</p>
    <img :src="this.img" alt="restaurant image" />
  </div>
</template>

<script>
export default {
    data() {
        return {
            restaurant: {
                cuisine: "",
                name: "",
                status: "",
                img: "../../dinerBackground.jpg",
            }
        }
    }
}
</script>

<style>

</style>