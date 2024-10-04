<template>
  <div class="full-app-body">
    <div class="nav">
      <!-- Do nav next -->
      <div class="nav-links">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'groups' }">Groups</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
        <span v-if="$store.state.token != ''">
        </span>
      </div>

      <h1 class="logo">Feast Finder</h1>
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
      } else {
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
  max-height: 100%;
  background-color: #00FFFF;
}

.full-app-body {
  display: grid;
  justify-items: center;
  align-items: center;
  height: 100%;
}

#capstone-app {
  display: grid;
  justify-items: center;
  align-items: start;
  color: #E6E6FA;
  background-repeat: repeat-x;
  /* Repeat horizontally */
  background-size: 100vw auto;
  /* Expand to full width of the viewport, auto height */
  background-position: top;
  height: 92vh;
  width: 100%;
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
  align-items: center;
}

.nav {
  display: grid;
  grid-template-columns: 2fr 5fr 2fr;
  background-color: #8A2BE2;
  align-content: center;
  font-size: 1.2em;
  width: 100%;
  height: 8vh;
  justify-items: center;
}

.nav-links {
  display: flex;
  font-size: 1.2em;
  justify-content: space-around;
  align-items: center;

  gap: 0 20px;
  width: 100%;
  font-family: Poppins, sans-serif;
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
