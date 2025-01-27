<template>
  <div>
    <h1>Level1</h1>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. </p>
    <Terminal/>
    <div class="icon-container">
      <i class="pi pi-angle-left icon"></i>
      <div class="right-icons">
        <i class="pi pi-lightbulb icon" @click="showModal('lightbulb')"></i>
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
      if (type === 'lightbulb') {
        this.modalContent = 'This is the lightbulb hint!';
      } else if (type === 'key') {
        this.modalContent = 'This is the key hint!';
      }
    },
    closeModal() {
      this.isModalVisible = false;
      this.modalContent = '';
    }
  }
}
</script>

<style scoped>
h1 {
  color: black;
}
p {
  color: #569191;
}
.icon-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 600px; /* gleiche Breite wie das Terminal */
  margin: 20px 0;
}
.right-icons {
  display: flex;
  gap: 10px; /* Abstand zwischen den Symbolen */
}
.icon {
  font-size: 24px;
  cursor: pointer;
}
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
}
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  max-width: 500px;
}
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>