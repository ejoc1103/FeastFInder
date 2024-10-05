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
        // return axios.post('/votes', poll)
    },

}