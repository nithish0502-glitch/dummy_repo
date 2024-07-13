import React from 'react';
import ReactDOM from 'react-dom';

const fname="Nithish";
const lname="Kumar";
ReactDOM.render(
  <div>
  <h1>My Favourite Foods</h1>
  <ul>
    <li>Biriyani</li>
    <li>{fname+" "+lname}</li>
    <li>Chicken</li>
  </ul>
  </div>, document.getElementById('root')
);
