<template>
 
    <form v-on:submit.prevent="register" id="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="inputs-area">
        <label for="username">Email Address</label>
        <input type="text" id="username" v-model="user.username" required autofocus />


        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />


        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />

      </div>
      <button type="submit" id="create-account">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }" :style="{ color: 'white' }" v-on:click="changeBackground">Already have an account? Log
          in.</router-link></p>
    </form>

</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    isPasswordValid() {
      const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
      return pattern.test(this.user.password);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else if (!this.isPasswordValid()) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter, and 1 number.';
      }

      else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.changeBackground();
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
    changeBackground() {
      this.$store.commit('UPDATE_BACKGROUND', '../../dinerBackground.jpg');
    }
  },
};
</script>

<style scoped>
#register {
  display: grid;
  justify-items: center;
  font-size: larger;
  background: #2e4053;
  color: white;
  border-radius: 10px;
  width: 100%;
  gap: 10px;
  padding: 3%;
  margin: 3%;
}

#create-account {
  background-color: #009688;
  font-size: larger;
  color: white;
  border: none;
  border-radius: 10px;
  padding: 10px;
  width: 100%;
}

.inputs-area {
  display: grid;
  gap: 10px;
  grid-template-columns: 1fr 1fr;
}
</style>
