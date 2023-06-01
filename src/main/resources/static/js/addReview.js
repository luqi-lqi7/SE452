document.getElementById("form").addEventListener("submit", function (e) {
  e.preventDefault();
  const content = document.getElementById("content").value;
  const star = document.getElementById("star").value;

  console.log(content, star);

  window.location.href = "reviews.html";
});
