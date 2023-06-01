// async function init() {
//     const response = await axios.get("https://api.apis.guru/v2/list.json");
//     console.log(response.data);
// }

// init();

document.getElementById("form").addEventListener("submit", function (e) {
  e.preventDefault();
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  console.log(email, password);

  window.location.href = "restaurants.html";
});
