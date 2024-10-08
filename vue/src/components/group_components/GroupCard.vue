<template>
  <div>
    <div class="card">
      <div class="card-header">
        <h3>{{ group.vote_name }}</h3>
      </div>
      <div class="card-body">
        
      </div>
    </div>
  </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
export default {
    props: ['group'],
    method: {
        getEateries(vote_id) {
            return VoteService.getEateries(vote_id).then(response => {
                console.log(response.data);
                this.restaurants = response.data;
            }).catch(e => {
                console.log(e);
            });
        }
    }
}
</script>

<style>

</style>