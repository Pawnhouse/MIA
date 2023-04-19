import {Component} from '@angular/core';
import {ComplaintService} from "../../services/complaint.service";

@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent {
  complaints: any[] = [];

  constructor(private complaintService: ComplaintService) {
  }

  ngOnInit(): void {
    this.complaintService.getComplaints().subscribe((data: any[]) => {
      this.complaints = data;
    });
  }

  onDelete(id: number) {
    this.complaintService.deleteComplaint(id).subscribe(
      () => this.complaints = this.complaints.filter((complaint) => complaint.id != id)
    );
  }

}
