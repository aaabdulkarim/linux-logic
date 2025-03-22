// Test WebSocket
const socket = new WebSocket("http://192.168.178.27:8000/ws");

// Elements^^
var input = document.getElementById("inputTerminal");
var button = document.getElementById("sendButton");


function appendUlElement(outputString, className) {
  var ulEl = document.getElementById("ausgaben");
  var liOutputEl = document.createElement("article")
  liOutputEl.className = className
  liOutputEl.innerHTML = outputString
  ulEl.appendChild(liOutputEl)

}



// Listen for messages
socket.addEventListener("message", (event) => {
  console.log("Output", event.data);
  appendUlElement(event.data, "left");
});



function handleInputAction(input){
  socket.send(input.value);
  appendUlElement(input.value, "right");
  input.value = ''

}


// HTML JS Functionality
button.addEventListener("click", (event) => handleInputAction(input));
input.addEventListener(
    "keydown", function (e) {
    if (e.key === 'Enter') {
      handleInputAction(input)
    }
});

