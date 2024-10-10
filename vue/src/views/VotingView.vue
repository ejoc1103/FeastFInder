<template>
  <div id="voting-container">
    <!-- Display vote name -->
    <h1>{{ vote.vote_name }}</h1>
    
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

      <!-- Copy URL Button -->
      <button @click="copyURL" class="copy-url-button">Copy Page URL</button>
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
      VoteService.setVoterName(this.voter_name, this.$route.params.id).then(response => {
        this.$store.commit('SET_VOTER_ID', response.data.voter_id);
        this.showVoteBoard = true;
      }).catch(e => {
        console.log(e);
      });
    },
    copyURL() {
      // Copy the current page URL to the clipboard
      const url = window.location.href;
      navigator.clipboard.writeText(url).then(() => {
        alert("URL copied to clipboard!");
      }).catch(e => {
        console.log("Failed to copy URL", e);
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

    VoteService.getEateries(this.$route.params.id)
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
/* Voting Container Styles */
#voting-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: 'Arial', sans-serif;
  background-color: #f0f0f0;
}

/* Title styling */
#voting-container h1 {
  font-size: 2rem;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
}

/* Form Style */
#form-style {
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  width: 300px;
  text-align: center;
}

#form-style label {
  font-size: 1rem;
  color: #333;
  margin-bottom: 5px;
}

#form-style input[type="text"] {
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
}

#form-style button {
  background-color: #4CAF50;
  color: white;
  padding: 10px;
  font-size: 1rem;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

#form-style button:hover {
  background-color: #45a049;
}

/* Voting Board Visibility */
#expired-container {
  text-align: center;
  background-color: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

#expired-container h1 {
  color: #ff0000;
  font-size: 1.8rem;
}

/* Button for copying URL */
.copy-url-button {
  background-color: #008CBA;
  color: white;
  padding: 12px 20px;
  font-size: 1rem;
  border-radius: 5px;
  border: none;
  margin-top: 20px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.copy-url-button:hover {
  background-color: #007b8d;
}

/* Responsiveness */
@media screen and (max-width: 768px) {
  #voting-container {
    padding: 10px;
  }

  #form-style {
    width: 80%;
  }

  .copy-url-button {
    width: 100%;
  }
}

</style>
