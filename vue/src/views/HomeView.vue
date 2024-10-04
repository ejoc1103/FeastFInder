<template>
  <div class="home">
    <div class="category-container">
      <div 
        class="category" 
        v-for="category in categories" 
        :key="category"
      >
        <button class="button-category" @click="findEatery(category.toString())">{{ category }}</button>
      </div>
    </div>
    <EateryFinderFormVue v-on:submit.prevent="findEatery" />
    <EateryList :restaurants="restaurants" />
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
        "Restaurants",
        "Bars",
        "Coffee & Tea",
        "Bakeries",
        "Pizza",
        "Sushi Bars",
        "Vegan",
        "Ice Cream & Frozen Yogurt",
        "Burgers",
        "Seafood",
      ],
    };
  },
  components: {
    EateryFinderFormVue,
    EateryList,
  },
  methods: {
    findEatery(cat) {
      this.restaurants = [];
      if(cat != '') {
        this.$store.commit("SET_CATEGORY", cat);
      }
      this.$store.commit("TOGGLE_RESTAURANTS", true);
    },
  },
};
</script>

<style scoped>
.home {
  display: grid;
  justify-items: center;
  align-items: start;
  padding: 10px;
  max-height: 92vh;
}
.category-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 5px;
  justify-items: center;
  margin-bottom: 10px;
}

.category {
  font-size: 1.2em;
  color: white;
  background-color: #00FFFF;
  margin: 0;
}

.button-category {
  font-size: 1em;
  color: #424242;
  background-color: rgba(41, 41, 173, 0);
  border: 2px solid white;
  border-radius: 5px;
  width: 100%;
  margin: 0;
  cursor: pointer;
}
</style>
