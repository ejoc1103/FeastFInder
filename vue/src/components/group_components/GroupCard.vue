<template>
  <div>
    <div class="card">
      <div class="card-header">
        <EateryCard :restaurants="restaurants" />
      </div>
      <div class="card-body">

      </div>
    </div>
  </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
import EateryCard from '../eatery_components/EateryCard.vue';
export default {
  data() {
    return {
      restaurants: [],
    };
  },
  props: ['group'],
  components: {
    EateryCard,
  },
  created() {
    this.restaurants = VoteService.getEateries(this.group.vote_id).then(response => {
      console.log(this.group.vote_id);
      console.log(response.data);
      this.restaurants = response.data;
    }).catch(e => {
      console.log(e);
    });
  }

}

</script>

<style></style>