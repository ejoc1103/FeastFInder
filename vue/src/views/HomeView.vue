<template>
  <div class="home">
    <div class="search-form-container">
      <h3 class="form-head">Restaurant Finder</h3>
      <div class='full-search-form'>

        <EateryFinderFormVue />
        <div class="popular-search-area">

          <h3 class="form-head">Popular Quick Searches</h3>
          <div class="category-container">
            <button v-for="category in categories" :key="category" class="category" @click="setCategory(category)">{{
              category }}</button>

          </div>
        </div>
      </div>
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
    };
  },
  components: {
    EateryFinderFormVue,
    EateryList,
  },
  methods: {
    setCategory(category) {
      this.$store.commit("SET_CATEGORY", category);
    },
  },
  created() {
    navigator.geolocation.getCurrentPosition(({ coords }) => {
      Math.floor(coords.latitude),
        Math.floor(coords.longitude),
        this.$store.commit("SET_LOCATION", {
          latitude: Math.floor(coords.latitude),
          longitude: Math.floor(coords.longitude),
        });
    }
    );

  }

};
</script>

<style scoped>
.home {
  display: grid;
  justify-items: center;
  align-items: start;
  padding: 10px;

}

.category-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 5px;
  justify-items: center;
}

.category {
  color: #E6E6FA;
  font-size: 1em;
  background-color: #8A2BE2;
  min-width: 10vw;
  border-radius: 20px;
  padding: 5px;
  margin: 0;
}

.full-search-form {
  display: grid;
  grid-template-columns: 1fr 1fr;
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
  background-color: #228B22;
  border-radius: 30px;
  justify-items: center;
}
</style>
