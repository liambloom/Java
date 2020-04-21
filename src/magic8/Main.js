// This is the equivalent code in js
// Keep in mind there is no html here, EVERYTHING is in js

const responses = [
  "It is certain",
  "It is decidedly so",
  "Without a doubt",
  "Yes, definitely",
  "You may rely on it",
  "As I see it, yes",
  "Most likely",
  "Outlook good",
  "Yes",
  "Signs point to yes",
  "Reply hazy, try again",
  "Ask again later",
  "Better not tell you now",
  "Cannot predict now",
  "Concentrate and ask again",
  "Don't count on it",
  "My reply is no",
  "My sources say no",
  "Outlook not so good",
  "Very doubtful"
];

document.body.style.setProperty("display", "flex");
document.body.style.setProperty("background-color", "#8800ff");
document.body.style.setProperty("flex-direction", "column");
document.body.style.setProperty("place-content", "center");
document.body.style.setProperty("align-items", "center");
document.body.style.setProperty("height", "100vh");

const question = document.createElement("input");
question.placeholder = "Question";
question.style.setProperty("text-align", "center");
question.style.setProperty("width", "200px");
question.style.setProperty("height", "20px");
question.addEventListener("keydown", event => {
  if (event.keyCode === 13) answer();
});
document.body.appendChild(question);

const submit = document.createElement("input");
submit.type = "button";
submit.value = "Submit";
submit.style.setProperty("width", "80px");
submit.style.setProperty("height", "20px");
submit.style.setProperty("text-align", "center");
submit.addEventListener("click", answer);
document.body.appendChild(submit);

const answerDiv = document.createElement("div");
answerDiv.style.setProperty("text-align", "center");
answerDiv.style.setProperty("height", "20px");
document.body.appendChild(answerDiv);

function answer () {
  question.value = "";
  question.blur();
  answerDiv.innerHTML = responses[Math.floor(Math.random() * 20)];
}