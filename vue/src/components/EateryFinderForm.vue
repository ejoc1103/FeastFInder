<template>
    <div class="eatery-finder-form">
        <h1>Eatery Finder</h1>
        <form v-on:submit.prevent="findEatery" v-if="showForm">
            <div class="form-input-group">
                <label for="City">City</label>
                <input type="text" v-model="eatery.city">
            </div>
            <div class="form-input-group">
                <label for="Zip Code">Zip Code</label>
                <input type="number" v-model="eatery.zipCode">
            </div>
            <button type="submit" v-on:click="toggleForm">Find Eatery</button>
        </form>
        <div v-else>
            <button>Show Form</button>
        </div>
    </div>
    
</template>

<script>
export default {
    data() {
        return {
            eatery: {
               zipCode: "",
               city: "",
            },
            showForm: true,
        };
    },
    methods: {
        findEatery() {
            console.log('Finding eatery...');
            this.$router.push()
        },
        toggleForm(){
            this.showForm = !this.showForm;
        }
    },
};


</script>

<style>
.eatery-finder-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 50px;
    background-color: #f0f0f0;
}
</style>