<template>
  <div>
    <EateryCard :restaurants="getRestaurants" />
  </div>
</template>

<script>
import EateryCard from '../components/eatery_components/EateryCard.vue';
export default {
  components: {
    EateryCard
  },
  computed: {
    getRestaurants() {
      console.log(this.$store.state.groups.length);
      return this.$store.state.groups[this.$route.params.id].eateries;
    }
  }
}
</script>

<style></style>