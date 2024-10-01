<template>
  <div>
    <h1>{{restaurant.name}}</h1>
    <p>{{ restaurant.status }}</p>
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
            }
        }
    }
}
</script>

<style>

</style>