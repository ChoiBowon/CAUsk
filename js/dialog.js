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

function categoryDirect(){
  if(document.join.category_selbox.value == 'direct'){
    document.join.CategoryDirect.disabled = false;
    document.join.CategoryDirect.value = "";
    document.join.CategoryDirect.focus();
  }else{
    document.join.CategoryDirect.disabled = true;
    document.join.CategoryDirect.value = document.join.category_selbox.options[document.join.category_selbox.selectedIndex].value;
  }
  console.log(CategoryDirect.value);
}

function sizeDirect(){
  console.log("size들어옴");
  if(document.join.size_selbox.value == 'direct'){
    document.join.SizeDirect.disabled = false;
    document.join.SizeDirect.value = "";
    document.join.SizeDirect.focus();
  }else{
    document.join.SizeDirect.disabled = true;
    document.join.SizeDirect.value = document.join.size_selbox.options[document.join.size_selbox.selectedIndex].value;
  }
  console.log(document.join.SizeDirect.value);

}

function hot(){
  console.log("hot들어옴");
  document.join.hotDirect.disabled = true;
  //console.log(document.join.hot_selbox.options[document.join.hot_selbox.selectedIndex].value);
  document.join.hotDirect.value = document.join.hot_selbox.options[document.join.hot_selbox.selectedIndex].value;
  console.log(document.join.hotDirect.value);
}


document.addEventListener("DOMContentLoaded", function() {
  showChart(false);
  $("#myBtn").addEventListener("click", addMenuListener);
  $("#close").addEventListener("click", closeMenuListener);
  $("#submit").addEventListener("click", closeMenuListener);


})
