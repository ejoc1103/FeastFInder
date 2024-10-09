<template>
  <div id="voting-container">
    <div id="form-style">
      <form @submit.prevent="setVoterName">
        <label for="voterName">Name:</label>
        <input type="text" id="voter-name" name="voterName" v-model="voter_name">
        <button type="submit">Submit Name</button>
      </form>
    </div>
    <EateryCard v-if="showVoteBoard" :restaurants="restaurants" />
  </div>
</template>

<script>
import VoteService from '../services/VoteService.js';
import EateryCard from '../components/eatery_components/EateryCard.vue';
export default {
  data() {
    return {
      restaurants: [],
      voter_name: '',
      showVoteBoard: false,
    };
  },
  components: {
    EateryCard,
  },
  methods: {
    setVoterName() {
      VoteService.setVoterName(this.voter_name, this.$route.params.id).then(response => {
        this.$store.commit('SET_VOTER_ID', response.data.voter_id);
        this.showVoteBoard = true;
      }).catch(e => {
        console.log(e);
      });
    }
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

<style>
#form-style {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #E6E6FA;
  background-color: #8A2BE2;
  padding: 20px;
  border-radius: 20px;
}

#voting-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
