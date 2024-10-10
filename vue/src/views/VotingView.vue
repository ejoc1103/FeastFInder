<template>

  <div id="voting-container">
    <div v-if="!isVoteExpired">
      <div id="form-style">
        <form @submit.prevent="setVoterName">
          <!-- If this works use it  -->
          <label for="voterName">Name:</label>
          <input type="text" id="voter-name" name="voterName" v-model="voter_name">
          <button type="submit">Submit Name</button>
        </form>
      </div>

      <EateryCard v-if="showVoteBoard" :restaurants="restaurants" />
    </div>
    <div v-else id="expired-container">
      <h1>This Vote Has Expired!</h1>
    </div>
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
      vote: {},
      currentDate: new Date(),
    };
  },
  components: {
    EateryCard,
  },
  methods: {
    setVoterName() {
      console.log(this.restaurants);
      VoteService.setVoterName(this.voter_name, this.$route.params.id).then(response => {
        this.$store.commit('SET_VOTER_ID', response.data.voter_id);
        this.showVoteBoard = true;
      }).catch(e => {
        console.log(e);
      });
    }
  },
  created() {

    VoteService.getVote(this.$route.params.id)
      .then(response => {
        this.vote = response.data;
      })
      .catch(e => {
        console.log(e);
      });

    return VoteService.getEateries(this.$route.params.id)
      .then(response => {
        this.restaurants = response.data;
      })
      .catch(e => {
        console.log(e);
      });


  },
  computed: {
    isVoteExpired() {
      return new Date(this.vote.vote_date) < this.currentDate;
    }
  }

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

#expired-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #8A2BE2;
  padding: 20px;
  border-radius: 20%;
  color: #E6E6FA;
}
</style>
