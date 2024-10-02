<template>
  <div id="login-container">
    <div id="login">
      <form v-on:submit.prevent="login" id="login-form">
        <h3>Please Sign In</h3>
        <div id="form-info">
          
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Email Address</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button type="submit" id="sign-in">Sign in</button>
          <p>
            <router-link v-bind:to="{ name: 'register' }" :style="{ color: '#009688' }">Need
            an
            account? Sign
            up.</router-link>
          </p>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  }
};
</script>

<style scoped>
#login-container {
  height: 100vh;
}
#login {
  display: flex;
  flex-direction: column;
  font-weight: bold;
  font-size: larger;
  background: #009688;
  color: #F5F5F5;
  border-radius: 10px;
  width: 100%;
  padding: 5%;
  margin: 5%;
  text-align: center;
}

#login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  width: 100%;
}

#form-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  width: 90%;
  background-color: #F5F5F5;
  color: #009688;
  border-radius: 10px;
  padding: 10px 0;
}

#sign-in {
  background-color: #FFEB3B;
  color: #424242;
  font-size: larger;
  border-radius: 10px;
  padding: 3%;
  margin: 1%;
}

.form-input-group {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: column;
}

label {
  margin-right: 0.5rem;
}
</style>