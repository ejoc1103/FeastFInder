<template>
    <form id="eater-finder-form" v-on:submit.prevent="setSearch">
        <div class="form">
            <h3>Restaurant Finder</h3>
            <div class="form-input-group">
                <label for="search">Zipcode/City:</label>
                <input type="text" v-model="search" />
            </div>
            <button type="submit">Find Eatery</button>
        </div>
    </form>
</template>

<script>
export default {
    data() {
        return {
            search: "",
        };
    },
    methods: {
        setSearch() {
            this.$store.commit('SET_SEARCH_TERM', this.search);
        },
    },
};

</script>

<style scoped>
.eatery-finder-form {
    display: flex;
    flex-direction: row;
    width: 50%;
    justify-content: center;
    background-color: #fa0626;
}

.eatery-finder-form>* {
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
