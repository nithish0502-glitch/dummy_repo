let addAttendance = (event)=>{
    event.preventDefault();
    
    const attendanceTable = document.getElementById("attendanceTable");
    const attendanceCount = document.getElementById("attendanceCount");

    const attendanceId = document.getElementById("attendanceid").value;
    const attendanceDate = document.getElementById("date").value;
    const attendancePercentage = document.getElementById("attendancePercentage").value;
    const attendeeName = document.getElementById("name").value;
    const courseName = document.getElementById("courseName").value;

    const newRow = attendanceTable.insertRow();
    newRow.innerHTML = `<td>${attendanceId}</td>
    <td>${attendeeName}</td>
    <td>${courseName}</td>
    <td>${attendanceDate}</td>
    <td>${attendancePercentage}</td>`;

    attendanceCount.textContent = `Number of records: ${attendanceTable.rows.length-1}`;
}
document.getElementById("submitButton").addEventListener("click", addAttendance);