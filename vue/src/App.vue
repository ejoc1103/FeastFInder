<template>
  <div>
  <div id="nav">
    <router-link v-bind:to="{ name: 'home' }" :style="{ color: 'white' }">Home</router-link>&nbsp;|&nbsp;
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"
      :style="{ color: 'white' }">Logout</router-link>
  </div>
  <div id="capstone-app" :style="{ backgroundImage: `url(${$store.state.currentBackground})` }">
    <div id="#main-view">
      <router-view />
    </div>

  </div>
</div>
</template>

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
