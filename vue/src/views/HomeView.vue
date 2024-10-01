<template>
  <div class="home">
    <eatery-finder-form-vue />
    <div class="eatery-grid">
      <eatery-card v-for="n in 25" v-bind:key="n"/>
    </div>
  </div>
</template>

<script>
import EateryFinderFormVue from "../components/EateryFinderForm.vue";
import EateryCard from "../components/EateryCard.vue";
export default {
  components: {
    EateryFinderFormVue,
    EateryCard,
  },
};
</script>
<style scoped>
.eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  background-color:aqua;
} 
.home {
  width: 100%;
  
}
</style>
