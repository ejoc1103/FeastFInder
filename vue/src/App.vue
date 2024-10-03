<template>
  <div>
    <div id="nav">
      &nbsp;|&nbsp;<router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'group' }" :style="{ color: 'white' }">Group</router-link>&nbsp;|&nbsp;
      <span v-if="$store.state.token != ''">
        <router-link v-bind:to="{ name: 'logout' }" :style="{ color: 'white' }">Logout</router-link> | 
      </span>
    </div>
    <div id="capstone-app" :style="{ backgroundImage: `url(${currentBackground})` }">
      <div id="main-view">
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
      } else if (pathName === 'group') {
        return "../../groupBackground.jpg";
      } else {
        return "../../dinerBackground.jpg";
      }
    },
  },
};
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  font-family: Poppins;
  height: 100%;
  overflow: hidden;
}

#capstone-app {
  display: grid;
  justify-content: center;
  align-items: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  height: 100vh;
  width: 100vw;
}

#nav {
  font-size: xx-large;
  background-color: #009688;
  width: 100%;
  text-align: center;
}

#main-view {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}
</style>
