import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function FoodSearchR() {
  const [data, setData] = useState(null);
  const [foodNm, setFoodNm] = useState("");
  const userid = sessionStorage.getItem("userid");
  const navigate = useNavigate();

  const navigateToFood = () => {
    navigate("/todo");
  };

  const fetchData = () => {
    if (foodNm) {
      fetch(`${config.SERVER_URL}/request/foodname/${foodNm}`)
        .then((response) => response.json())
        .then((data) => setData(data))
        .catch((error) => console.error("Error fetching data:", error));
    }
  };

  const handleButtonClick = (item) => {
    console.log(item);
    const itemWithUserId = { ...item, userid };

    fetch("${config.SERVER_URL}/upload/recordfood", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(itemWithUserId),
    })
      .then((response) => response.json())
      .then((data) => console.log("Success:", data))
      .catch((error) => console.error("Error:", error));
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
      <button onClick={navigateToFood}> 뒤로가기 (임시)</button>
    </div> //
  );
}
