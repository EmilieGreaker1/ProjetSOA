document.getElementById("loginForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = {
        userid: document.getElementById("userid").value,
        password: document.getElementById("password").value
    };

  //  if (!formData.email || !formData.password) {
  //      alert("All fields must be filled out.");
   //     return;
 //   }

    try {
        // Send GET request with the retrieved URL
        const response = await fetch(`http://localhost:8090/login/` + formData.userid + "+" + formData.password, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            
            const jsonLoginResp = await response.json();
            console.log(jsonLoginResp);

            if(jsonLoginResp.userId !== 0) {
                // alert("Login successful!");

                if(document.getElementById("userType").value === "Admin") {
                    //alert("Redirecting admin...");
                    window.location.href = "../../views/AdmViewRequests.html";
                }
                else if(document.getElementById("userType").value === "Volunteer") {
                    // alert("Redirecting volunteer...");
                    window.location.href = "../../views/VolMyMissions.html"; // Redirect to my Missions page
                }
                else {
                    //alert("Redirecting other - Person in need...");
                    window.location.href = "../../views/UsrHelpRequest.html";
                }
            }
            else {
                alert("User does not exist, please try again");
            }
        } else {
            const error = await response.json();
            alert(`Error: ${error.message || "Incorrect email or password"}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});