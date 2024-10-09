<template>
  <div>
    <div class="card">
      <div class="card-header">
        <EateryCard :restaurants="restaurants" :upVotes="upVotes" :downVotes="downVotes" />
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
      upVotes: [],
      downVotes: [],
    };
  },
  props: ['group'],
  components: {
    EateryCard,
  },
  created() {
    this.restaurants = VoteService.getEateries(this.group.vote_id).then(response => {
      this.restaurants = response.data;
      this.restaurants.forEach((group) => {
          VoteService.getVoteTrueCount(group.eatery_id)
            .then((response) => {
              console.log("dad");
              console.log(response.data);
              this.upVotes.push(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
          VoteService.getVoteFalseCount(group.eatery_id)
            .then((response) => {
              this.downVotes.push(response.data);
            })
            .catch((e) => {
              console.log(e);
            });
        });
    }).catch(e => {
      console.log(e);
    });
  }

}

</script>

<style></style>