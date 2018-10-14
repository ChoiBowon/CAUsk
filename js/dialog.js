function $(id) {
  return document.querySelector(id);
}

function showChart(isVisible) {
     if(isVisible) $("#myModal").classList.add("visible");
     else $("#myModal").classList.remove("visible");
}

function addMenuListener(evt) {
  evt.preventDefault();
    showChart(true);
}

function closeMenuListener(evt) {
  evt.preventDefault();
    showChart(false);
}

document.addEventListener("DOMContentLoaded", function() {
  showChart(false);
  $("#myBtn").addEventListener("click", addMenuListener);
  $("#close").addEventListener("click", closeMenuListener);
})
