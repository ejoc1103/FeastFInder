<template>
  <div class="home">

    <div v-if="showForm" class="search-form-container">
      <div class='full-search-form'>
        <EateryFinderFormVue />
        <div class="options">
        <div class="popular-search-area">
          <div v-if="seePopular" class="category-container">
            <button v-for="category in categories" :key="category" class="category" @click="setCategory(category)">{{
              category }}</button>
          </div>
        </div>
        <button @click="toggleSeePopular">{{ seePopular ? 'Hide Popular Searches' : 'Show Popular Quick Searches' }}</button>
        <button @click="showForm = !showForm">Hide Form</button>
        </div>
      </div>
    </div>
    <div v-else>
      <button @click="showForm = !showForm">Search Filters</button>
    </div>
    <EateryList v-show="restaurants.length < 2" :restaurants="restaurants" />
  </div>

</template>

<script>
import EateryFinderFormVue from "../components/eatery_components/EateryFinderForm.vue";
import EateryList from "../components/eatery_components/EateryList.vue";
export default {
  data() {
    return {
      restaurants: [],
      categories: [
        "Best restaurants near me",
        "Top-rated restaurants",
        "Popular restaurants in my area",
        "Family-friendly restaurants",
        "Romantic restaurants",
        "Cheap eats near me",
        "Best places to eat",
        "Vegan restaurants near me",
        "Gluten-free restaurants",
        "Healthy restaurants",
        // "Restaurants with outdoor seating",
        // "Late-night restaurants",
        // "Restaurants with delivery",
        // "Seafood restaurants near me",
        // "Fine dining restaurants",
        // "Fast food near me",
        // "Best brunch spots",
        // "Restaurants with live music",
        // "Mexican restaurants near me",
        // "Asian cuisine near me"
      ],
      seePopular: false,
      showForm: false,
    };
  },
  components: {
    EateryFinderFormVue,
    EateryList,
  },
  methods: {
    setCategory(category) {
      this.$store.commit('SET_CATEGORY', '');
      this.$store.commit("SET_CATEGORY", category);
    },
    toggleSeePopular() {
      this.seePopular = !this.seePopular;
    },
  },
};
</script>

<style scoped>
.home {
  display: grid;
  justify-items: center;
  align-items: start;
}

.category-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 10px;
  justify-items: center;
}

.category {
  color: #e6faeb;
  font-size: 1em;
  background-color: #8A2BE2;
  min-width: 10vw;
  border-radius: 20px;
  padding: 5px;
  margin: 0;
}

.full-search-form {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  align-items: start;
}

.popular-search-area {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
}

.search-form-container {
  display: grid;
  grid-template-columns: 1fr;
  color: #8A2BE2;
  font-size: 1.2em;
  background-color: #E6E6FA;
  border-radius: 30px;
  justify-items: center;
  padding: 15px 25px 5px 25px;
}

button {
  color: #f2fae6;
  background-color: #228B22;
  border-radius: 20px;
  padding: 15px;
  margin: 5px;
}

.options {
  display: flex;
  flex-direction: row;
  padding: 5px;
}
</style>
