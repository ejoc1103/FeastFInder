<template>
  <div>
    <div id="eatery-grid" v-if="!$store.state.moreDetailsView">
      <div v-for="restaurant in restaurants" :key="restaurant.eatery_id" class="card-view"
        :style="{ backgroundImage: `url(${restaurant.image_url})` }">
        <div id="card-grid">
          <h2 class="restaurant-name">{{ restaurant.eatery_name }}</h2>
          <div id="status-container">
            <h3 id="status">
              {{ restaurant.isClosed ? "Sorry They're Closed" : "Open Now!" }}
            </h3>
            <h3 id="category">
              {{ restaurant.category }}
            </h3>
          </div>

          <div v-if="getPathName === 'home'" :style="{ gridArea: 'buttons' }" class="votes">
            <div v-for="group in groups" :key="group.vote_id" :name="group.vote_id">
              <button v-if="!group.restaurants.includes(restaurant)"
                @click=" addEateryToVote(group, restaurants.indexOf(restaurant))"
                :disabled="hasVoted[restaurants.indexOf(restaurant)]">
                {{ group.vote_name }}
              </button>
            </div>
          </div>

          <div v-if="getPathName === 'groups'" class="votes">
            <h3>{{ this.upVotes[restaurants.indexOf(restaurant)] }} Up Votes</h3>
            <h3>{{ this.downVotes[restaurants.indexOf(restaurant)] }} Down Votes</h3>
          </div>

          <div
            v-if="getPathName === 'voting' && isValid[restaurants.indexOf(restaurant)] && !hasVoted[restaurants.indexOf(restaurant)]"
            class="votes">
            <button @click="castVote(true, restaurant.eatery_id, restaurants.indexOf(restaurant))" class="thumbs-up">
              <i class="fas fa-thumbs-up"></i>
            </button>
            <button @click="castVote(false, restaurant.eatery_id, restaurants.indexOf(restaurant))" class="thumbs-down">
              <i class="fas fa-thumbs-down"></i>
            </button>
          </div>

          <div>
            <button @click="showMoreInfo(restaurant)">{{ this.showMoreOrLess ? "Show Less" : "Show More" }}</button>
          </div>
        </div>
      </div>
    </div>

<<<<<<< HEAD
    <div v-if="$store.state.moreDetailsView" class="more-details-view">
=======
    <div v-if="$store.state.moreDetailsView" class="details-view-container">
>>>>>>> 81dee641a255c8cdc93296b2d7cd0df0a7a9f0ac
      <EateryDetail :restaurantDetails="restaurantDetails" />
    </div>


  </div>
</template>

<script>
import VoteService from "../../services/VoteService";
import EateryDetail from '../eatery_components/EateryDetail.vue';
export default {
  data() {
    return {
      showMoreOrLess: false,
      newRestaurant: {
        eatery_id: "",
        eatery_name: "",
        image_url: "",
        eatery_address: "",
        category: "",
        website: "",
        open_time: "",
        close_time: "",
        has_takeout: false,
        rating: 0,
        phone: "",
        price: "",
        isClosed: false,
        city: "",
        isValid: [],
      },
      seeAddress: false,
      groups: [],
      restaurantDetails: {},
    };
  },
  props: ['restaurants', 'upVotes', 'downVotes'],
  components: {
    EateryDetail,
  },
  methods: {
    castVote(vote, eatery_id, index) {
      VoteService.castVote(vote, eatery_id, this.$store.state.voter_id)
        .then(() => {
          this.isValid[index] = false;
          this.hasVoted[index] = true;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    showMoreOrLessMethod() {
      this.showMoreOrLess = !this.showMoreOrLess;
    },
    showMoreInfo(restaurant) {
      if (restaurant) {
        this.restaurantDetails = restaurant;
      } else {
        this.restaurantDetails = null;
      }
      this.$store.commit('TOGGLE_DETAILS_VIEW');
    },
    addEateryToVote(group, pickedId) {
      let pickedRestaurant = this.restaurants[pickedId];

      this.groups[this.groups.indexOf(group)].restaurants.push(pickedRestaurant);
      VoteService.addEatery(group.vote_id, pickedRestaurant)
        .then((response) => {
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });


    },
    formatTime(time) {
      let [hours, minutes] = time.split(":");
      if (!minutes) {
        hours = time.slice(0, 2);
        minutes = time.slice(2, 4);
      }
      hours = parseInt(hours, 10);
      let suffix = hours >= 12 ? "PM" : "AM";
      hours = hours % 12 || 12;
      return `${hours}:${minutes} ${suffix}`;
    },
  },
  computed: {
    buttonName() {
      return this.showMoreOrLess ? "Show Less" : "Show More";
    },
    getPathName() {
      let pathName = this.$route.name;
      return pathName;
    },
    checkPathName() {
      let pathName = this.$route.name;

      if (pathName === 'home') {
        return 'card-view';
      } else if (pathName === 'groups' && this.$store.state.moreDetailsView) {
        return 'more-details-view';
      } else if (pathName === 'voting') {
        return 'voting-view';
      }
      return pathName;
    }
  },
  created() {
    if (Array.isArray(this.restaurants) && this.restaurants.length > 0) {
      this.isValid = this.restaurants.map(() => true);
      this.hasVoted = this.restaurants.map(() => false);
    } else {
      console.error("No restaurants data available or data is not in the correct format.");
    }

    VoteService.getVotes()
      .then((response) => {
        this.groups = response.data;
        for (let i = 0; i < this.groups.length; i++) {
          this.groups[i].restaurants = [];
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }
};
</script>

<style>
.website-link {
  color: #f2fae6;
  background-color: #E6E6FA;
  /* Increase padding */
  box-shadow: 8px 8px 25px rgba(164, 36, 115, 0.8);
  /* Increase shadow intensity and transparency */
  font-size: 1.2em;
  /* Larger font size */
  font-weight: bold;
  /* Make text bolder */
  border: 2px solid #a42473;
  /* Add a contrasting border */
  cursor: pointer;
  transition: all 0.3s ease;
  /* Smooth transition for hover effects */
  cursor: pointer;
}

a:hover {
  background-color: #f88f1f;

  box-shadow: 10px 10px 30px rgba(164, 36, 115, 1);

  transform: scale(1.05);

}

button {
  background-color: #8A2BE2;
  color: white;
  border: none;
  border-radius: 30px;
  margin: 5px;
}

.card-view {
  display: grid;
  align-items: stretch;
  border-radius: 10px;
  border: 5px solid #8A2BE2;
  text-align: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 26vw;
  min-height: 70vh;
}

.details-view-container {
  grid-column: span 3;
  display: grid;
  grid-template-columns: 1fr;
  border-radius: 10px;
  border: 5px solid #00FFFF;
  text-align: center;
  align-items: center;
  padding: 5px;
  margin: 5px;
  background-size: cover;
  width: 80vw;
  background: linear-gradient(50deg,

      #00FFFF,

      #228B22,

      #FF69B4,

      #8A2BE2,

      #7FFF00,

      #f7a029);
}

<<<<<<< HEAD

=======
>>>>>>> 81dee641a255c8cdc93296b2d7cd0df0a7a9f0ac
.website-link {
  font-size: 1.2em;
  text-decoration: none;
  color: #8A2BE2;
  background-color: #00FFFF;
  border-radius: 30px;
  justify-self: center;
  align-self: center;
  padding: 5px 10px;
}

#card-grid {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: stretch;
  align-items: start;
}

#eatery-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  justify-content: center;
  align-items: stretch;
}

.restaurant-name {
  grid-column: span 3;
  border-radius: 5px;
  margin: 0;
  background-color: #22b6b2;
  border-radius: 30px;
  padding: 1vw;
}

.votes {
  border-radius: 5px;
  margin: 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

#status-container {
  grid-column: span 3;
  background-color: rgb(247, 160, 41, 0.55);
  padding: 10px;
  border-radius: 10px;
  text-align: center;
}

#status {
  color: #2f855a;
  /* Green for open */
}

#status-container #status:contains("Closed") {
  color: #c53030;
  /* Red for closed */
}

/* Category container styling */
#category-container {
  text-align: center;
  background-color: rgb(247, 160, 41, 0.55);
  padding: 5px;
  border-radius: 10px;
  text-align: center;
}

#category {
  font-weight: bold;
}

#hours-container {
  grid-column: span 2;
  display: grid;
  grid-template-columns: 1fr 1fr;
  background-color: rgb(247, 160, 41, 0.55);
  border-radius: 30%;
  padding: 2px;
}

#open-time,
#close-time {
  color: #8A2BE2;
  /* Dark gray */
}

#city {
  grid-column: span 2;
  color: #00FFFF;
  /* Dark gray */
}

.thumbs-up {
  background-color: #4CAF50;
  /* Green color */
  box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
  /* Green shadow */
  margin-right: 10px;
  padding: 10px;
}

.thumbs-up:hover {
  background-color: #45a049;
  /* Darker green on hover */
  transform: scale(2);
  /* Grow to twice the size */
}

/* Thumbs Down Button Styles */
.thumbs-down {
  background-color: #f44336;
  /* Red color */
  box-shadow: 0px 4px 6px rgba(255, 0, 0, 0.3);
  /* Red shadow */
  padding: 10px;
}

.thumbs-down:hover {
  background-color: #e53935;
  /* Darker red on hover */
  transform: scale(2);
  /* Grow to twice the size */
}

i {
  font-size: 1.5em;
  color: white;
}
</style>