<template>
  <div>
    <h1>DAD</h1>
    <EateryCard :restaurants="restaurants" />
  </div>
</template>

<script>
import VoteService from '../services/VoteService.js';
import EateryCard from '../components/eatery_components/EateryCard.vue';
export default {
  data() {
    return {
      restaurants: [],
    };
  },
  components: {
    EateryCard,
  },
  created() {
    return VoteService.getEateries(this.$route.params.id)
        .then(response => {
          this.restaurants = response.data;
        })
        .catch(e => {
          console.log(e);
        });
  },
};
</script>

<style></style>
