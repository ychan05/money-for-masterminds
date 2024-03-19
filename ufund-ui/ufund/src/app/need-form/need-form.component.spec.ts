import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedFormComponent } from './need-form.component';

describe('NeedFormComponent', () => {
  let component: NeedFormComponent;
  let fixture: ComponentFixture<NeedFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NeedFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NeedFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
