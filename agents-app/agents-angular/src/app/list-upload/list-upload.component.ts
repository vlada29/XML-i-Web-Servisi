import {Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UploadFileService} from '../upload-file.service';
import {UnitComponent}from '../unit/unit.component';

@Component({
  selector: 'list-upload',
  templateUrl: './list-upload.component.html',
  styleUrls: ['./list-upload.component.css']
})
export class ListUploadComponent implements OnInit {

  showFile = false
  public fileUploads: Observable<string[]>
  public specific: Observable<string[]>

  constructor(private uploadService: UploadFileService,
              private unitComp: UnitComponent,
              private elementRef:ElementRef
  ) {}

  ngOnInit() {

  }
  @Input()
  slikeUrls: string[];


  showFiles(enable: boolean) {

      //console.log('ref:',this.elementRef.nativeElement.parentElement.parentElement.parentElement);
    this.showFile = enable
    //console.log('slikeUrls u list upload: ', this.slikeUrls);
    if (enable) {
        //this.unitComp.fileUploadInUnit = this.uploadService.getFiles();
        //this.unitComp.filesPathsOnly = this.uploadService.getFiles().toArray();

        this.uploadService.getFiles().subscribe(data=>{
            var pravi = data;
            var kaoStringovi = data as string[];
            var kopirani = JSON.parse(JSON.stringify(data as string[]));
            console.log('u showFiles this.unitComp.slike:',this.unitComp.slike);
            console.log('kaoStringovi',kaoStringovi);

                for (let entry of kaoStringovi) {

                    console.log('sad:',entry);
                    if(this.unitComp.slike.indexOf(entry.toString()) == -1){

                        kopirani.splice(kopirani.indexOf(entry), 1);
                    }
            }


            console.log('na kraju kopirani: ', kopirani);
            this.fileUploads = Observable.of(kopirani);
        });

        //this.fileUploads = this.uploadService.getFiles();
        //console.log('fileUploads - > ',this.fileUploads); //undefined


        //this.fileUploads = Observable.of(this.unitComp.slike);

    }
  }
}
