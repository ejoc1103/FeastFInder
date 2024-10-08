import axios from 'axios';

export default {
    getVotes() {
        return axios.get(`/votes`);
    },
    getVote(id) {
        return axios.get(`/vote/${id}`)
    },
    createGroup(poll) {
        console.log(poll);
        return axios.post(`/vote`, poll);
    },
    addEatery(voteId, eatery) {
        console.log(eatery);
        return axios.post(`/eatery/${voteId}`, eatery);
    },
    getEateries(voteId) {
        return axios.get(`/eateries/${voteId}`);
    },
    getEatery(voteId) {
        return axios.get(`/eatery/${voteId}`);
    }
    
}