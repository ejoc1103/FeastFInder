<template>
    <div class="eatery-finder-form">
        <h3>Restaurant Finder</h3>
        <form @submit.prevent="setSearchTerm" id="form"> <!-- Add .prevent here to prevent page reload -->
            <div class="form-input-group">
                <label for="search">Zipcode/City:</label>
                <input type="text" v-model="search" />
            </div>
            <button type="submit">Find Eatery</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            search: "",  // The search input model
        };
    },
    methods: {
        setSearchTerm() {
            // Commit the search term to the Vuex store
            this.$store.commit('SET_SEARCH_TERM', this.search);
        }
    },
};
</script>

<style scoped>
.eatery-finder-form {
    display: flex;
    flex-direction: row;
    width: 50%;
    justify-content: center;
    background-color: red;
}
.eatery-finder-form > * {
    margin: 10px;
}
.form-input-group {
    display: flex;
    flex-direction: row;
}
#form {
    display: flex;
    flex-direction: row;
}
</style>