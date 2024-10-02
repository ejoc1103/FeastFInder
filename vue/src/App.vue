<template>
  <div>
    <div id="nav">
      &nbsp;|&nbsp;<router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
        :style="{ color: 'white' }">Logout</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'group' }" :style="{ color: 'white' }">Group</router-link>&nbsp;|&nbsp;
    </div>
    <div id="capstone-app" :style="{ backgroundImage: `url(${currentBackground})` }">
      <div id="#main-view">
        <router-view />
        {{ $route.name }}
      </div>

    </div>
  </div>
</template>
<script>
export default {
  //not sure if computed is the best way to do this for speed purposes but it works for now
  computed: {
    currentBackground() {
      console.log("dad");
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
body {
  margin: 0;
  padding: 0;
  font-family: Poppins;
  height: 100%;
}

#capstone-app {
  display: grid;
  justify-content: center;
  align-items: center;
  background-repeat: repeat;
  background-size: 100vw;
  background-position: center;
  height: 100%;
}

#nav {
  font-size: xx-large;
  background-color: #009688;
  width: 100%;
}
</style>
