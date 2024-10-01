<template>
  <div class="home">
    <eatery-finder-form-vue/>
    <eatery-card />
  </div>
</template>

<script>
import EateryFinderFormVue from '../components/EateryFinderForm.vue';
import EateryCard from '../components/EateryCard.vue';
export default {
  components: {
    EateryFinderFormVue,
    EateryCard
  }
};
</script>
