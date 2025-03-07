import React, { useState } from "react";

export default function FoodSearchR() {
  const [data, setData] = useState(null);
  const [foodNm, setFoodNm] = useState("");

  const fetchData = () => {
    if (foodNm) {
      fetch(`http://172.30.113.136:8080/request/foodname/${foodNm}`)
        .then((response) => response.json())
        .then((data) => setData(data))
        .catch((error) => console.error("Error fetching data:", error));
    }
  };

  const handleButtonClick = (item) => {
    console.log(item);
  };

  return (
    <div>
      <input
        type="text"
        value={foodNm}
        onChange={(e) => setFoodNm(e.target.value)}
        placeholder="Enter food name"
      />
      <button onClick={fetchData}>Search</button>
      {data ? (
        <div>
          {data.map((item, index) => (
            <button key={index} onClick={() => handleButtonClick(item)}>
              {item.foodNm} {item.mfrNm}
            </button>
          ))}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}
