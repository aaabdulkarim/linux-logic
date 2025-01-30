<template>
<div class="all grid">
  <div class="container">
    <div class="header ">
      <h1>Level1</h1>
    </div>
    <div class="content">
      <h2>Aufgabenstellung</h2>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.</p>
    </div>
    <div class="terminal-container">
      <Terminal/>
    </div>
    <div class="icon-container">
      <i class="pi pi-angle-left icon"></i>
      <div class="right-icons">
        <i class="pi pi-pencil icon" @click="showModal('edit')"></i>
        <i class="pi pi-key icon" @click="showModal('key')"></i>
        <i class="pi pi-angle-right icon"></i>
      </div>
    </div>
    
    <div v-if="isModalVisible" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <p>{{ modalContent }}</p>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Terminal from './Terminal.vue';
export default {
  name: 'Anforderung',
  components: {
    Terminal
  },
  data() {
    return {
      isModalVisible: false,
      modalContent: ''
    };
  },
  methods: {
    showModal(type) {
      this.isModalVisible = true;
      this.modalContent = type === 'edit' ? 'Hier kannst du deine Aufgabe bearbeiten!' : 'Dies ist der Schlüsselhinweis!';
    },
    closeModal() {
      this.isModalVisible = false;
      this.modalContent = '';
    }
  }
}
</script>

<style scoped>
.all {
  background-color: white;
}
.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 1200px;
  padding-left: 20px;
  padding-right: 20px;
}
.content {
  justify-content: left;
  text-align: left;
  padding-left: 20px;
  padding-right: 20px;

}

.header {
  padding: 10px;
  color: white;
}

.terminal-container {
  width: 100%;
  max-width: 1200px;
  border-radius: 8px;
  margin-top: 20px;
}

.icon-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin-top: 20px;
  padding: 0 20px;
  padding-bottom: 18rem;
}

.right-icons {
  display: flex;
  gap: 15px;
}

.icon {
  font-size: 24px;
  cursor: pointer;
}
</style>
