import React from "react";

import config from "../config";

const ClickThis = () => {
  const handleClick = async () => {
    try {
      const response = await fetch(`http://${config.SERVER_URL}/request/up`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      console.log("Response:", data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <button onClick={handleClick}>Send Request</button>
    </div>
  );
};

export default ClickThis;
