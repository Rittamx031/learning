const myHeading = document.querySelector("h1");
myHeading.textContent = "hello world";
console.log(typeof myHeading);
// if else statement

if (myHeading) {
  /* condition*/
  console.log(true);
} else {
  console.log(false);
}

const mannerresult = document.getElementById("manner_result");
const select = document.querySelector("select");

select.addEventListener("change", setManner);

function setManner(){
  const chooie = select.value;
  switch (chooie) {
    case "trang":
      mannerresult.textContent = "duma m ngunhu cho khi chon trang";
      break;
    case "hue":
      mannerresult.textContent = "duma m ngunhu cho hue";
      break;
    case "nhai":
      mannerresult.textContent = "duma m ngunhu cho nhai";
      break;
    default:
      mannerresult.textContent = "duma m ngunhu cho";
  }
};
