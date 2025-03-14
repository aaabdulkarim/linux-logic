<template>
    <div class="content">
        <h1>App Download</h1>
    <div class="qr-container">
        <img class="qr-code" src="@/assets/QR_Code.png" alt="QR Code" />
        <button class="action-button p-3 mb-3 text-lg font-bold">Download App</button>
    </div>
    </div>
</template>

<script>
export default {
  name: "QRCodeButton",
};
</script>

<style scoped>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #f4f4f4;
  padding-bottom: 11rem;
}
.qr-container {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 150px;
  padding: 20px;
}

.qr-code {
  width: 300px;
  height: 300px;
}

.action-button {
    color: white;
    background-color: #569191;
    border: 2px solid #569191;
    border-radius: 10px;
    transition: background-color 0.3s ease;
    width: 300px;
    height: 80px;
}
  
.action-button:hover {
    color: #569191;
    background-color: white;
    border: 2px solid #569191;
}

/* Mobile Ansicht */
@media (max-width: 768px) {
  .qr-container {
    flex-direction: column;
  }
}
</style>


