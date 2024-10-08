<template>
  <div class="full-app-body">
    <div class="nav" v-show="$store.state.token != ''">
      <!-- Do nav next -->
      <div class="nav-links">
        <router-link v-show="getPathName !== 'home'" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-show="getPathName !== 'groups'" v-bind:to="{ name: 'groups' }">Groups</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
      </div>

      <h1 class="logo">Feast Finder</h1>
      <router-link v-if="$store.state.groups.length > 0" v-bind:to="{ name: 'voting', params: { id: '0' } }">View
        Votes</router-link>
    </div>
    <div id="capstone-app" :style="{ backgroundImage: `url(${currentBackground})` }">
      <div :id="idSelector">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    getPathName() {
      return this.$route.name;
    },
    currentBackground() {
      let pathName = this.$route.name;
      if (pathName === 'home') {
        return "../../homeBackground.jpg";
      } else if (pathName === 'login') {
        return "../../loginBackground.jpg";
      } else if (pathName === 'register') {
        return "../../registerBackground.jpg";
      } else if (pathName === 'groups') {
        return "../../groupsBackground.jpg";
      } else {
        return "../../dinerBackground.jpg";
      }
    },
    idSelector() {
      let pathName = this.$route.name;
      if (pathName === 'groups') {
        return 'groups-main';
      } else if (pathName === 'login') {
        return 'login-main';
      } else if (pathName === 'register') {
        return 'register-main';
      }
      else {
        return 'home-main';
      }
    }
  },
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  font-family: "Poppins", sans-serif;
  font-weight: 500;
  font-style: italic;
  height: 100%;
  background-color: #00FFFF;
}

.full-app-body {
  display: grid;
  justify-items: center;
  align-items: center;
  height: 100%;
}

#capstone-app {
  padding-top: 10px;
  display: grid;
  justify-items: center;
  color: #E6E6FA;
  background-repeat: repeat;
  background-size: cover;
  max-height: 100vh;
  min-height: 92vh;
  width: 100%;
  padding: 15px;
  box-sizing: border-box; 
}

#groups-main {
  display: flex;
  justify-content: center;
  align-items: start;
  gap: 20px;
  max-width: 90vw;
}

#home-main {
  display: flex;
  justify-content: center;
  align-items: start;
  max-width: 90vw;
}

#login-main {
  display: grid;
  grid-template-columns: 1fr;
  align-content: center;
  justify-content: center;
  max-height: 100vh;
  height: 95vh;
}

#register-main {
  display: grid;
  grid-template-columns: 1fr;
  align-content: center;
  max-height: 100vh;
  height: 95vh;
}

.nav {
  display: grid;
  grid-template-columns: 3fr 5fr 3fr;
  background-color: #8A2BE2;
  align-content: center;
  font-size: 1.2em;
  width: 100%;
  height: 8vh;
  justify-items: center;
}

.nav-links {
  display: grid;
  gap: 1vw;
  grid-template-columns: 1fr 1fr 1fr;
  font-size: 1.2em;
  align-items: center;
  justify-items: center;
}

.nav-links a {
  color: #E6E6FA;
  text-decoration: none;
}

.logo {
  font-size: 2em;
  color: #7FFF00;
  font-family: "Shrikhand", serif;
  font-weight: 400;
  font-style: normal;
  margin: 0;
  padding: 0;
}
</style>
